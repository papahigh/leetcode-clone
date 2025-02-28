import { useCallback, useEffect, useState } from 'react';
import { useGetLatest, useUnmounted } from '~/shared/hooks';

import { type CreateSubmission, Status, type SubmissionDetails } from '~/shared/types';

import { api } from '~/shared/api';

const POLLING_INTERVAL = 1000;

export function useSubmission() {
  const [feedback, setFeedback] = useState<SubmissionDetails | null>(null);
  const [pollingId, setPollingId] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const isUnmounted = useUnmounted();
  const isSubmitting = useGetLatest(submitting);

  const close = useCallback(() => {
    setFeedback(null);
    setPollingId(null);
    setSubmitting(false);
  }, []);

  const submit = useCallback(
    async function (request: CreateSubmission) {
      try {
        setFeedback(null);
        setSubmitting(true);

        const response = await api.createSubmission(request);
        if (!isUnmounted() && isSubmitting()) {
          setFeedback(response);
          setPollingId(response.id);
        }
      } finally {
        if (!isUnmounted()) setSubmitting(false);
      }
    },
    [isUnmounted, isSubmitting],
  );

  useEffect(() => {
    let timeoutID: NodeJS.Timeout | null = null;

    function _schedule() {
      timeoutID = setTimeout(async function () {
        timeoutID = null;
        const response = await api.getSubmissionById(pollingId!);
        if (!isUnmounted()) {
          setFeedback(response);
          if (needsReschedule(response)) {
            _schedule();
          } else {
            setPollingId(null);
          }
        }
      }, POLLING_INTERVAL);
    }

    if (pollingId != null) _schedule();
    return () => {
      if (timeoutID != null) clearTimeout(timeoutID);
    };
  }, [pollingId, isUnmounted]);

  return {
    isOpen: submitting || feedback != null,
    isLoading: submitting || pollingId != null,
    feedback,
    submit,
    close,
  };
}

function needsReschedule(it: SubmissionDetails) {
  return [Status.EVALUATION_PENDING, Status.EVALUATION_STARTED].includes(it.status);
}

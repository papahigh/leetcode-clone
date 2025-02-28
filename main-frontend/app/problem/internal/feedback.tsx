import type { HTMLProps } from 'react';
import { Status, type SubmissionDetails } from '~/shared/types';

import { AnimatedLoader } from '~/shared/ui/animated-loader';

import prettyBytes from 'pretty-bytes';
import { className } from '~/shared/utils';

interface FeedbackProps extends HTMLProps<HTMLDivElement> {
  feedback: SubmissionDetails | null;
}

export function Feedback(props: FeedbackProps) {
  return (
    <div className={className('flex h-full w-full', props.className)}>
      <Centered>{renderContent(props.feedback)}</Centered>
    </div>
  );
}

function renderContent(submission: SubmissionDetails | null) {
  switch (submission?.status) {
    case null:
    case Status.EVALUATION_PENDING:
    case Status.EVALUATION_STARTED:
      return <AnimatedLoader />;

    case Status.RUNTIME_ERROR:
    case Status.EVALUATION_FAILED:
      return <strong className="text-4xl text-red-500">Runtime Error</strong>;

    case Status.COMPILATION_ERROR:
      return <strong className="text-4xl text-red-500">Compilation Error</strong>;

    case Status.MEMORY_LIMIT_EXCEEDED:
      return <strong className="text-4xl text-red-500">Memory Limit Exceeded</strong>;

    case Status.TIME_LIMIT_EXCEEDED:
      return <strong className="text-4xl text-red-500">Time Limit Exceeded</strong>;

    case Status.WRONG_ANSWER:
      return (
        <>
          <strong className="mb-4 text-5xl text-red-500">Wrong Answer</strong>
          <pre>
            <code className="text-xl">{submission.stderr}</code>
          </pre>
        </>
      );

    case Status.ACCEPTED: {
      return (
        <>
          <strong className="mb-4 text-5xl text-green-500">Accepted</strong>
          <div>
            <p className="text-xl">
              <strong>Time</strong> {submission.time}ms
            </p>
            <p className="text-xl">
              <strong>Memory</strong> {prettyBytes(submission.memory ?? 0)}
            </p>
          </div>
        </>
      );
    }
  }
}

function Centered(props: HTMLProps<HTMLDivElement>) {
  return <div className="flex h-full w-full flex-col items-center justify-center" {...props} />;
}

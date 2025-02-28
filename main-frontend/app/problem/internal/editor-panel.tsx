import { useRef } from 'react';
import { IoClose as CloseIcon } from 'react-icons/io5';

import { Button } from '~/shared/ui/button';
import { CodeEditor, type CodeEditorRef } from '~/shared/ui/code-editor';
import { LangSelect } from '~/shared/ui/lang-select';
import { Language, type ProblemDetails } from '~/shared/types';
import { className } from '~/shared/utils';

import { Feedback } from './feedback';
import { PanelContent } from './panel-content';
import { useProblem } from './use-problem';
import { useSubmission } from './use-submission';

interface EditorPanelProps {
  problem: ProblemDetails;
}

export function EditorPanel({ problem }: EditorPanelProps) {
  const editorRef = useRef<CodeEditorRef | null>(null);
  const submission = useSubmission();
  const { code, lang, langs, setLang } = useProblem(problem);

  function onSubmit() {
    submission.submit({
      problemId: problem.id,
      lang,
      code: editorRef.current?.getValue() ?? '',
    });
  }

  function onLangChange(newLang: Language) {
    setLang(newLang);
    submission.close();
  }

  function renderFooter() {
    return (
      <PanelContent>
        <section
          className={className(
            'relative',
            'transition-property-height duration-250 ease-in-out',
            submission.isOpen ? 'h-[360px]' : 'h-0 overflow-hidden',
          )}>
          <div
            className={className(
              'flex h-full flex-col flex-wrap items-center justify-center',
              'transition-property-opacity duration-250 ease-in-out',
              submission.isOpen ? 'opacity-100' : 'opacity-0',
            )}>
            <Button
              size="none"
              type="text"
              className={className(
                'absolute top-0 right-0',
                'text-3xl text-stone-400 hover:text-stone-200',
              )}
              onClick={submission.close}>
              <CloseIcon />
            </Button>
            {submission.isOpen && <Feedback feedback={submission.feedback} />}
          </div>
        </section>
        <section className="flex h-[48px] items-center justify-between px-4">
          <LangSelect
            value={lang}
            options={langs}
            onChange={onLangChange}
            disabled={submission.isLoading}
          />
          <Button size="lg" type="green" onClick={onSubmit} disabled={submission.isLoading}>
            Submit
          </Button>
        </section>
      </PanelContent>
    );
  }

  return (
    <div className="flex h-full w-full flex-col">
      <div className="flex-1 overflow-hidden">
        <PanelContent>
          <CodeEditor ref={editorRef} language={lang} value={code} className="pt-6" />
        </PanelContent>
      </div>
      <footer className="mt-4">{renderFooter()}</footer>
    </div>
  );
}

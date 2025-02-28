import { useMemo, useState } from 'react';
import { Language, type ProblemDetails } from '~/shared/types';

const DEFAULT_LANGUAGE = Language.KOTLIN;

export function useProblem(problem: ProblemDetails) {
  const [lang, setLang] = useState<Language>(DEFAULT_LANGUAGE);
  const langs = useMemo(() => problem.snippets.map((it) => it.lang).sort(), [problem.snippets]);
  const code = useMemo(
    () => problem.snippets.find((it) => it.lang === lang)?.code || '',
    [lang, problem.snippets],
  );
  return { lang, setLang, langs, code };
}

// Generated using typescript-generator version 3.2.1263 on 2025-02-27 18:03:53.

export interface ProblemSummary {
  id: number;
  slug: string;
  name: string;
}

export interface ProblemDetails {
  id: number;
  slug: string;
  name: string;
  body: string;
  snippets: ProblemSnippet[];
}

export interface CreateSubmission {
  problemId: number;
  lang: Language;
  code: string;
}

export interface SubmissionDetails {
  id: string;
  createdAt: Date;
  updatedAt: Date;
  problemId: number;
  status: Status;
  lang: Language;
  code: string;
  stdout: string | null;
  stderr: string | null;
  memory: number | null;
  time: number | null;
}

export interface ProblemSnippet {
  code: string;
  lang: Language;
}

export const enum Language {
  C = 'C',
  CPP = 'CPP',
  CSHARP = 'CSHARP',
  DART = 'DART',
  ELIXIR = 'ELIXIR',
  ERLANG = 'ERLANG',
  GO = 'GO',
  JAVA = 'JAVA',
  JAVA_SCRIPT = 'JAVA_SCRIPT',
  KOTLIN = 'KOTLIN',
  PHP = 'PHP',
  PYTHON3 = 'PYTHON3',
  RUBY = 'RUBY',
  RUST = 'RUST',
  SWIFT = 'SWIFT',
  TYPE_SCRIPT = 'TYPE_SCRIPT',
}

export const enum Status {
  EVALUATION_PENDING = 'EVALUATION_PENDING',
  EVALUATION_STARTED = 'EVALUATION_STARTED',
  EVALUATION_FAILED = 'EVALUATION_FAILED',
  COMPILATION_ERROR = 'COMPILATION_ERROR',
  RUNTIME_ERROR = 'RUNTIME_ERROR',
  TIME_LIMIT_EXCEEDED = 'TIME_LIMIT_EXCEEDED',
  MEMORY_LIMIT_EXCEEDED = 'MEMORY_LIMIT_EXCEEDED',
  WRONG_ANSWER = 'WRONG_ANSWER',
  ACCEPTED = 'ACCEPTED',
}

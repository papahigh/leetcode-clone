import type { Monaco, OnMount } from '@monaco-editor/react';
import { Language } from '~/shared/types';

const LANGUAGE_MAPPINGS = {
  [Language.C]: 'c',
  [Language.CPP]: 'cpp',
  [Language.CSHARP]: 'csharp',
  [Language.DART]: 'dart',
  [Language.ELIXIR]: 'elixir',
  [Language.ERLANG]: 'erlang',
  [Language.GO]: 'go',
  [Language.JAVA]: 'java',
  [Language.JAVA_SCRIPT]: 'javascript',
  [Language.KOTLIN]: 'kotlin',
  [Language.PHP]: 'php',
  [Language.PYTHON3]: 'python',
  [Language.RUBY]: 'ruby',
  [Language.RUST]: 'rust',
  [Language.SWIFT]: 'swift',
  [Language.TYPE_SCRIPT]: 'typescript',
};

/**
 * A function that maps a given language to its corresponding value.
 */
export const mapLanguage = (lang: Language) => LANGUAGE_MAPPINGS[lang];

/**
 * Callback function that is executed when the Monaco editor is mounted.
 */
export const onMount: OnMount = (monaco, editor) => {
  initTheme(editor);
  initErlang(editor);
  initTypeScript(editor);
  monaco.updateOptions({ theme: THEME });
};

const THEME = 'leetcode-clone';

function initTheme(monaco: Monaco | null) {
  monaco?.editor.defineTheme(THEME, {
    base: 'vs-dark',
    inherit: true,
    rules: [{ token: 'keyword', fontStyle: 'bold' }],
    colors: {
      'editor.background': '#00000000',
      'scrollbar.shadow': '#00000000',
    },
  });
}

function initTypeScript(monaco: Monaco | null) {
  const disableValidation = {
    noSemanticValidation: true,
    noSyntaxValidation: true,
  };
  monaco?.languages.typescript.typescriptDefaults.setDiagnosticsOptions(disableValidation);
  monaco?.languages.typescript.javascriptDefaults.setDiagnosticsOptions(disableValidation);
}

function initErlang(monaco: Monaco | null) {
  monaco?.languages.register({ id: 'erlang' });
  monaco?.languages.setLanguageConfiguration('erlang', {
    wordPattern: /[a-zA-Z_][a-zA-Z0-9_]*/,
    comments: {
      lineComment: '%',
    },
    brackets: [
      ['(', ')'],
      ['{', '}'],
      ['[', ']'],
    ],
    autoClosingPairs: [
      { open: '(', close: ')' },
      { open: '{', close: '}' },
      { open: '[', close: ']' },
      { open: '"', close: '"' },
    ],
    surroundingPairs: [
      { open: '(', close: ')' },
      { open: '{', close: '}' },
      { open: '[', close: ']' },
      { open: '"', close: '"' },
    ],
  });

  monaco?.languages.setMonarchTokensProvider('erlang', {
    defaultToken: '',
    tokenPostfix: '.erlang',
    tokenizer: {
      root: [
        [
          /\b(break|case|catch|of|end|fun|if|try|query|receive|after|when|and|or|not|div|rem)\b/,
          'keyword',
        ],
        [/-module\b/, 'keyword.metadata'],
        [/-export\b/, 'keyword.metadata'],
        [/-import\b/, 'keyword.metadata'],
        [/-spec\b/, 'keyword.metadata'],
        [/[a-z][a-zA-Z0-9_]*/, 'atom'],
        [/[A-Z][a-zA-Z0-9_]*/, 'variable'],
        [/\b\d+(\.\d+)?\b/, 'number'],
        [/"([^"\\]|\\.)*$/, 'string.invalid'],
        [/"([^"\\]|\\.)*"/, 'string'],
        [/%.*/, 'comment'],
      ],
    },
  });
}

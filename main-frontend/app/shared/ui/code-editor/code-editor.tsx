import { type Ref, useImperativeHandle, useRef } from 'react';
import { Editor as MonacoEditor, type EditorProps, type Monaco } from '@monaco-editor/react';

import { Language } from '~/shared/types';
import { mapLanguage, onMount } from './code-editor-utils';

import './code-editor-style.css';

export interface CodeEditorProps {
  ref?: Ref<CodeEditorRef>;
  value: string;
  language: Language;
  className?: string;
}

export interface CodeEditorRef {
  getValue: () => string;
}

export function CodeEditor({ ref, value, language, className }: CodeEditorProps) {
  const monacoRef = useRef<Monaco | null>(null);
  useImperativeHandle(ref, () => ({ getValue: () => getValue(monacoRef.current) }));
  return (
    <MonacoEditor
      theme="vs-dark"
      value={value}
      language={mapLanguage(language)}
      className={className}
      options={OPTIONS}
      onMount={onMount}
      beforeMount={(monaco) => (monacoRef.current = monaco)}
    />
  );
}

function getValue(monaco: Monaco | null) {
  return monaco?.editor.getEditors().at(0)?.getModel()?.getValue() ?? '';
}

const OPTIONS: EditorProps['options'] = {
  fontSize: 18,
  fontFamily: 'JetBrains Mono',
  fontLigatures: true,
  contextmenu: false,
  minimap: { enabled: false },
  scrollBeyondLastLine: false,
  stickyScroll: { enabled: false },
  overviewRulerLanes: 0,
  glyphMargin: false,
  quickSuggestions: false,
  parameterHints: { enabled: false },
  suggestOnTriggerCharacters: false,
  tabCompletion: 'off',
};

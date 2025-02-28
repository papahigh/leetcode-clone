import ReactMarkdown, { type Components } from 'react-markdown';

import './markdown-style.css';

export interface MarkdownProps {
  text?: string;
}

export function Markdown({ text }: MarkdownProps) {
  return (
    <section className="markdown">
      <ReactMarkdown components={components}>{text}</ReactMarkdown>
    </section>
  );
}

const components: Components = {
  h3: (p) => <h3 {...p} className="pt-3 pb-2 text-xl font-semibold text-balance text-stone-400" />,
  p: (p) => <p {...p} className="py-2 text-lg text-pretty" />,
  ul: (p) => <ul {...p} className="list-disc py-4 pl-6" />,
  li: (p) => <li {...p} className="pl-2 text-lg text-pretty" />,
  hr: (p) => <hr {...p} className="my-4 border-stone-400/20" />,
  pre: (p) => (
    <pre {...p} className="mt-2 mb-4 rounded-lg bg-stone-900/50 p-3 text-lg text-stone-400" />
  ),
  blockquote: (p) => <blockquote {...p} className="mt-2 mb-6 py-2 pl-8" />,
};

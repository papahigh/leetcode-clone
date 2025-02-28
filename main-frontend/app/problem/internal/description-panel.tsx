import { Markdown } from '~/shared/ui/markdown';
import { PanelContent } from './panel-content';

interface DescriptionPanelProps {
  name: string;
  body: string;
}

export function DescriptionPanel({ name, body }: DescriptionPanelProps) {
  return (
    <PanelContent scrollable innerClassName="px-10 py-8">
      <h1 className="pb-4 text-3xl text-balance">{name}</h1>
      <Markdown text={body} />
    </PanelContent>
  );
}

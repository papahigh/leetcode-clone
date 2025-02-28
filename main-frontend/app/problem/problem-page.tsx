import { useLoaderData } from 'react-router';
import { Panel, PanelGroup } from 'react-resizable-panels';

import { api } from '~/shared/api';

import { ResizeHandle } from '~/shared/ui/resize-handle';

import { EditorPanel } from './internal/editor-panel';
import { DescriptionPanel } from './internal/description-panel';

import type { Route } from './+types/problem-page';

export async function clientLoader(args: Route.ClientLoaderArgs) {
  const problem = await api.getProblemBySlug(args.params.slug);
  return { problem };
}

export function meta() {
  return [
    { title: 'LeetCodeClone' },
    { name: 'description', content: 'Welcome to LeetCodeClone!' },
  ];
}

export const links: Route.LinksFunction = () => [
  {
    rel: 'stylesheet',
    href: 'https://fonts.googleapis.com/css2?family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&display=swap',
  },
];

export default function ProblemPage() {
  const { problem } = useLoaderData();
  return (
    <PanelGroup direction="horizontal" autoSaveId="leetcode-clone:problem-page">
      <Panel minSize={20} defaultSize={40} maxSize={70}>
        <DescriptionPanel name={problem.name} body={problem.body} />
      </Panel>
      <ResizeHandle type="vertical" />
      <Panel defaultSize={60}>
        <EditorPanel problem={problem} />
      </Panel>
    </PanelGroup>
  );
}

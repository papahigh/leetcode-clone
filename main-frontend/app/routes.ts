import { index, route, layout, type RouteConfig } from '@react-router/dev/routes';

export default [
  layout('shared/layout/listing-layout.tsx', [index('problem-listing/problem-listing-page.tsx')]),
  layout('shared/layout/problem-layout.tsx', [
    route('/problems/:slug', 'problem/problem-page.tsx'),
  ]),
] satisfies RouteConfig;

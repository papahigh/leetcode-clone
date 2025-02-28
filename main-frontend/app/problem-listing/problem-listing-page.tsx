import { useLoaderData } from 'react-router';
import { ProblemListing } from './problem-listing';

import { api } from '~/shared/api';

import type { Route } from './+types/problem-listing-page';

import lockedProblems from './problem-listing.json';

export async function loader() {
  const unlockedProblems = await api.getAllProblems();
  return {
    meta: {
      title: 'LeetCodeClone',
      description: 'Welcome to LeetCodeClone!',
    },
    unlockedProblems,
    lockedProblems,
  };
}

export function meta({ data }: Route.MetaArgs) {
  return [{ title: data.meta.title }, { name: 'description', content: data.meta.description }];
}

export default function ProblemListingPage() {
  const { unlockedProblems, lockedProblems } = useLoaderData();
  return <ProblemListing unlockedProblems={unlockedProblems} lockedProblems={lockedProblems} />;
}

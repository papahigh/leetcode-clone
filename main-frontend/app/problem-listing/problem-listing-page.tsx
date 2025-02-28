import { useLoaderData } from 'react-router';
import { ProblemListing } from './problem-listing';

import { api } from '~/shared/api';

import lockedProblems from './problem-listing.json';

export async function loader() {
  const unlockedProblems = await api.getAllProblems();
  return {
    unlockedProblems,
    lockedProblems,
  };
}

export function meta() {
  return [
    { title: 'LeetCodeClone' },
    { name: 'description', content: 'Welcome to LeetCodeClone!' },
  ];
}

export default function ProblemListingPage() {
  const { unlockedProblems, lockedProblems } = useLoaderData();
  return <ProblemListing unlockedProblems={unlockedProblems} lockedProblems={lockedProblems} />;
}

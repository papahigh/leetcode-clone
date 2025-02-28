import { Link } from 'react-router';

import type { ProblemSummary } from '~/shared/types';

interface ProblemListingProps {
  lockedProblems: string[];
  unlockedProblems: ProblemSummary[];
}

export function ProblemListing({ lockedProblems, unlockedProblems }: ProblemListingProps) {
  return (
    <div className="flex w-[960px] flex-col gap-5 px-6 py-22 lg:px-0 xl:w-[1200px] 2xl:w-[1400px]">
      <div className="mb-8">
        <div className="flex h-[150px] flex-row gap-6">
          <div className="h-full w-full flex-1 rounded-xl bg-gradient-to-br from-teal-400 to-yellow-100" />
          <div className="h-full w-full flex-1 rounded-xl bg-gradient-to-tl from-amber-400 to-pink-400" />
          <div className="h-full w-full flex-1 rounded-xl bg-gradient-to-tr from-cyan-500 to-blue-500" />
        </div>
      </div>

      <div className="mb-8">
        <h3 className="mb-6 text-2xl">Study Plan</h3>
        <div className="flex h-[150px] flex-row gap-6">
          <div className="flex h-full flex-1 items-center justify-center rounded-xl bg-gradient-to-tl from-stone-700 to-stone-800"></div>
          <div className="flex h-full flex-1 items-center justify-center rounded-xl bg-gradient-to-t from-stone-700 to-stone-700"></div>
          <div className="flex h-full flex-1 items-center justify-center rounded-xl bg-gradient-to-tr from-stone-700 to-stone-800"></div>
        </div>
      </div>

      <h3 className="mb-2 text-2xl">Pick a Problem</h3>
      <div className="relative overflow-x-auto sm:rounded-lg">
        <table className="text-md w-full text-left text-stone-500">
          <thead className="bg-stone-800 text-lg text-stone-100">
            <tr>
              <th scope="col" className="px-6 font-medium">
                #
              </th>
              <th scope="col" className="py-4 font-medium">
                <span className="px-1">Title</span>
              </th>
              <th scope="col" className="px-6 py-4 text-center font-medium">
                Solution
              </th>
              <th scope="col" className="px-6 py-4 text-center font-medium">
                Acceptance
              </th>
              <th scope="col" className="px-6 py-4 text-center font-medium">
                Difficulty
              </th>
              <th scope="col" className="px-6 py-4 text-center font-medium">
                Frequency
              </th>
            </tr>
          </thead>
          <tbody>
            {unlockedProblems.map((problem, index) => (
              <tr key={index} className="border-b-1 border-b-stone-800 bg-stone-900 text-stone-100">
                <th scope="row" className="px-6 text-lg font-normal whitespace-nowrap">
                  {index + 1}
                </th>
                <th scope="row" className="py-4 text-lg font-normal whitespace-nowrap">
                  <Link to={`/problems/${problem.slug}`}>{problem.name}</Link>
                </th>
                <td className="px-6">
                  <div className="flex items-center justify-center text-stone-300">—</div>
                </td>
                <td className="px-6">
                  <div className="flex items-center justify-center text-stone-300">—</div>
                </td>
                <td className="px-6">
                  <div className="flex items-center justify-center text-stone-300">—</div>
                </td>
                <td className="px-6">
                  <div className="flex items-center justify-center text-stone-300">—</div>
                </td>
              </tr>
            ))}
            {lockedProblems.map((problem, index) => (
              <tr key={index} className="border-b-1 border-b-stone-800">
                <th scope="row" className="px-6 text-lg font-normal whitespace-nowrap">
                  {index + 1 + unlockedProblems.length}
                </th>
                <th scope="row" className="py-4 text-lg font-normal">
                  {problem}
                </th>
                <td className="px-6">
                  <div className="flex items-center justify-center">—</div>
                </td>
                <td className="px-6">
                  <div className="flex items-center justify-center">—</div>
                </td>
                <td className="px-6">
                  <div className="flex items-center justify-center">—</div>
                </td>
                <td className="px-6">
                  <div className="flex items-center justify-center">—</div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

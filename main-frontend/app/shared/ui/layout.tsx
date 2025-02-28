import type { HTMLProps } from 'react';
import { NavLink } from 'react-router';

import { className } from '~/shared/utils';

export function Header(props: HTMLProps<HTMLDivElement>) {
  return (
    <header
      className={className(
        'flex items-center justify-between',
        'h-[80px] w-full px-8',
        'border-b-1 border-stone-700/50 bg-stone-800',
        props.className,
      )}>
      <NavLink to={'/'} className="text-3xl">
        LeetCode<span className="text-amber-500">Clone</span>
      </NavLink>
      <NavLink
        to={'/'}
        className="hidden rounded-lg bg-amber-900/30 px-6 py-2.5 text-lg text-amber-400 md:block">
        a new way to learn
      </NavLink>
    </header>
  );
}

export function Content(props: HTMLProps<HTMLDivElement>) {
  return (
    <div
      className={className(
        'flex flex-col',
        'h-[calc(100%-80px)] w-full',
        'overflow-y-auto',
        'bg-stone-900',
        'lg:items-center',
        props.className,
      )}>
      {props.children}
    </div>
  );
}

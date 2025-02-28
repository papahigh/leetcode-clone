import type { HTMLProps } from 'react';
import { className } from '~/shared/utils';

interface PanelContentProps extends HTMLProps<HTMLDivElement> {
  scrollable?: boolean;
  outerClassName?: string;
  innerClassName?: string;
}

export function PanelContent(props: PanelContentProps) {
  return (
    <div className={className('h-full', props.scrollable && 'overflow-auto', props.outerClassName)}>
      <div
        className={className(
          props.scrollable ? 'min-h-full' : 'h-full',
          'min-w-[400px] rounded-lg bg-stone-800 p-4',
          props.innerClassName,
        )}>
        {props.children}
      </div>
    </div>
  );
}

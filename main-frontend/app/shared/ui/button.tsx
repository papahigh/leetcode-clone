import { type HTMLProps } from 'react';
import { className } from '~/shared/utils';

export interface ButtonProps extends Omit<HTMLProps<HTMLButtonElement>, 'size'> {
  size?: keyof typeof SIZES;
  type?: keyof typeof TYPES;
}

export function Button({ size, type, ...props }: ButtonProps) {
  return (
    <button
      {...props}
      className={className(
        'cursor-pointer font-semibold',
        'flex items-center select-none',
        'disabled:cursor-not-allowed disabled:opacity-50',
        'transition-colors duration-200',
        SIZES[size || 'md'],
        TYPES[type || 'text'],
        props.className,
      )}
    />
  );
}

const SIZES = {
  none: '',
  md: 'text-md rounded-md px-6 h-[32px]',
  lg: 'text-lg rounded-md px-8 h-[38px]',
} as const;

const TYPES = {
  text: '',
  green: 'bg-green-600 text-neutral-50 hover:bg-green-500',
  secondary: 'border-2 text-stone-200 border-stone-700 hover:border-stone-600',
} as const;

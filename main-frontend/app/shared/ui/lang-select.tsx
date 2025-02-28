import { useSelect } from 'downshift';

import { Button } from '~/shared/ui/button';
import { className } from '~/shared/utils';
import { Language } from '~/shared/types';

export interface SimpleSelectProps {
  value: Language;
  options: Language[];
  onChange: (value: Language) => void;
  disabled?: boolean;
}

export function LangSelect({ value, options, onChange, disabled }: SimpleSelectProps) {
  const {
    isOpen,
    selectedItem,
    getToggleButtonProps,
    getLabelProps,
    getMenuProps,
    highlightedIndex,
    getItemProps,
  } = useSelect({
    items: options,
    selectedItem: value,
    onSelectedItemChange: ({ selectedItem }) => onChange(selectedItem),
    getItemId: (index) => options[index],
    itemToString: getItemLabel,
    itemToKey: getItemKey,
  });

  return (
    <div className="relative">
      <div className="flex flex-col gap-1">
        <label {...getLabelProps()} className="sr-only">
          Choose language:
        </label>
        <Button type="secondary" size="lg" disabled={disabled} {...getToggleButtonProps()}>
          {getItemLabel(selectedItem)}
        </Button>
      </div>
      <ul
        {...getMenuProps()}
        className={className(
          !isOpen && 'hidden',
          'absolute top-0 -translate-y-[calc(100%+10px)]',
          'grid grid-flow-col grid-rows-8',
          'drop-shadow-3xl rounded-xl bg-stone-900',
          'text-lg text-stone-400',
          'p-4',
        )}>
        {options.map((item, index) => (
          <li
            {...getItemProps({ item, index })}
            key={getItemKey(item)}
            className={className(
              'transition-colors duration-100',
              'flex cursor-pointer flex-col px-4 py-2',
              (selectedItem === item || highlightedIndex === index) && 'text-white',
            )}>
            <span>{getItemLabel(item)}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}

const LABELS = {
  [Language.C]: 'C',
  [Language.CPP]: 'C++',
  [Language.CSHARP]: 'C#',
  [Language.DART]: 'Dart',
  [Language.ELIXIR]: 'Elixir',
  [Language.ERLANG]: 'Erlang',
  [Language.GO]: 'Go',
  [Language.JAVA]: 'Java',
  [Language.JAVA_SCRIPT]: 'JavaScript',
  [Language.KOTLIN]: 'Kotlin',
  [Language.PHP]: 'PHP',
  [Language.PYTHON3]: 'Python3',
  [Language.RUBY]: 'Ruby',
  [Language.RUST]: 'Rust',
  [Language.SWIFT]: 'Swift',
  [Language.TYPE_SCRIPT]: 'TypeScript',
};

const getItemKey = (item: Language | null) => item ?? '-';
const getItemLabel = (item: Language | null) => (item ? LABELS[item] : '-');

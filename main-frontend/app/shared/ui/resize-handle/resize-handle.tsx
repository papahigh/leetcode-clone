import { useState } from 'react';
import { PanelResizeHandle } from 'react-resizable-panels';

import { className } from '~/shared/utils';
import './resize-handle-style.css';

export interface ResizeHandleProps {
  type: 'horizontal' | 'vertical';
  disabled?: boolean;
  className?: string;
}

export function ResizeHandle(props: ResizeHandleProps) {
  const [focused, setFocused] = useState(false);
  return (
    <PanelResizeHandle
      disabled={props.disabled}
      onDragging={setFocused}
      className={className(
        props.type,
        'resize-handle',
        focused && 'focused',
        props.disabled && 'disabled',
        props.className,
      )}
    />
  );
}

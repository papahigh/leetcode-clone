import { useRef, useCallback, useEffect } from 'react';

export function useGetLatest<T>(value: T) {
  const ref = useRef(value);
  ref.current = value;
  return useCallback(() => ref.current, []);
}

export function useUnmounted() {
  const unmounted = useRef(false);
  useEffect(() => {
    unmounted.current = false;
    return function cleanup() {
      unmounted.current = true;
    };
  }, []);
  return useCallback(() => unmounted.current, []);
}

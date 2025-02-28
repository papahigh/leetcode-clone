export function className(...classNames: unknown[]) {
  return classNames.filter(Boolean).join(' ');
}

export function isSSR() {
  return typeof window === 'undefined' || window.document == null;
}

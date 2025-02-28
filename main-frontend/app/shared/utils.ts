export function className(...classNames: unknown[]) {
  return classNames.filter(Boolean).join(' ');
}

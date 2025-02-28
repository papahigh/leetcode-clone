import './animated-loader-style.css';

export function AnimatedLoader() {
  return (
    <div role="status" className="loader">
      <svg aria-hidden="true" className="inline h-20 w-20 text-stone-600" viewBox="0 0 84 40">
        <circle cx="10" cy="10" r="10" fill="currentColor" className="animate-bounce" />
        <circle cx="42" cy="10" r="10" fill="currentColor" className="animate-bounce" />
        <circle cx="74" cy="10" r="10" fill="currentColor" className="animate-bounce" />
      </svg>
      <span className="sr-only">Loading...</span>
    </div>
  );
}

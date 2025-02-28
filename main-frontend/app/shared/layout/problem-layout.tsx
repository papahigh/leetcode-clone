import { Outlet } from 'react-router';
import { Content, Header } from '~/shared/ui/layout';

export default function ProblemLayout() {
  return (
    <>
      <Header className="border-b-transparent bg-stone-950 shadow-none" />
      <Content className="bg-stone-950 px-4 pb-4">
        <Outlet />
      </Content>
    </>
  );
}

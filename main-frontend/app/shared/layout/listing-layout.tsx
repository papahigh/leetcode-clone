import { Outlet } from 'react-router';
import { Content, Header } from '~/shared/ui/layout';

export default function ListingLayout() {
  return (
    <>
      <Header />
      <Content>
        <Outlet />
      </Content>
    </>
  );
}

import { useNavigate } from 'react-router-dom';
import { UserContext } from './UserContext';
import { useEffect, useContext } from 'react';
import { useLocation } from 'react-router-dom';

export default function AuthCheck({ children }) {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    // console.log('user', user);

    // if there is no user, redirect to login
    if (!user) {
      navigate('/login');
      return;
    }

    // if the user's type is 1 and they are trying to access a restricted route, redirect to some allowed page
    const restrictedRoutes = ['/orders-list', '/users-list', '/book-list', '/admin-profile'];
    if (user.type === 1 && restrictedRoutes.includes(location.pathname)) {
      navigate('/');
    }
  }, [user, navigate, location]);

  return user ? children : null;
}

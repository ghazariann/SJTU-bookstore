import React from "react";
import {
  Routes, Route, BrowserRouter
} from "react-router-dom";
import HomeView from "./view/HomeView";
import ProfileView from "./view/ProfileView";
import ErrorPage from "./view/ErrorView";
import BookView from "./view/BookView";
import OrderView from "./view/OrderView";
import CartView from "./view/CartView";
import MainLayout from "./components/Layout";
import LoginView from "./view/LoginView";
import { UserProvider } from './UserContext';
import AuthCheck from './AuthCheck';
import AddBookView from "./view/AddBookView";
import RegisterView from "./view/RegisterView";
import UsersTable from "./view/UserListView";
import OrderListView from "./view/OrderListView";
import BooksTable from "./view/BookListView";
import WrappedRegisterForm from "./view/RegisterView";

export default function BasicRoute() {
    return (
      <UserProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/login" element={<LoginView />} />
            <Route path="/register" element={<RegisterView />} />
            <Route path="/" element={<MainLayout />}>
              <Route path="/" element={<AuthCheck><HomeView /></AuthCheck>} />
              <Route path="cart" element={<AuthCheck><CartView /></AuthCheck>} />
              <Route path="register" element={<AuthCheck><WrappedRegisterForm /></AuthCheck>} />
              <Route path="bookDetails" element={<AuthCheck><BookView /></AuthCheck>} />
              <Route path="orders" element={<AuthCheck><OrderView /></AuthCheck>} />
              <Route path="profile" element={<AuthCheck><ProfileView /></AuthCheck>} />
              <Route path="orders-list" element={<AuthCheck><OrderListView /></AuthCheck>} />
              <Route path="users-list" element={<AuthCheck><UsersTable /></AuthCheck>} />
              <Route path="book-list" element={<AuthCheck><BooksTable /></AuthCheck>} />
              <Route path="add-book" element={<AuthCheck><AddBookView /></AuthCheck>} />  
              <Route path="*" element={<ErrorPage />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </UserProvider>
    );
}

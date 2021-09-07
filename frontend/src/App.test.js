import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Slowniki react link', () => {
  render(<App />);
  const linkElement = screen.getByText('Slowniki');
  expect(linkElement).toBeInTheDocument();
});

test('renders Aplikacja react link', () => {
  render(<App />);
  const linkElement = screen.getByText('Aplikacja');
  expect(linkElement).toBeInTheDocument();
});

import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import App from '../App';

test('renders App component with WeatherSearchComponent', () => {
  render(<App />);
  expect(screen.getByText('Weather by city:')).toBeInTheDocument();
  expect(screen.getByTestId('search-input')).toBeInTheDocument();
  expect(screen.getByTestId('search-button')).toBeInTheDocument();
});

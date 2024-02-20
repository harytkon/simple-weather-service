import React from 'react';
import { render, fireEvent, waitFor, screen } from '@testing-library/react';
import WeatherSearchComponent from '../components/WeatherSearchComponent';

jest.mock('node-fetch');

describe('WeatherSearchComponent', () => {
  it('renders WeatherSearchComponent', () => {
    const { getByTestId } = render(<WeatherSearchComponent />);
    expect(getByTestId('search-input')).toBeInTheDocument();
    expect(getByTestId('search-button')).toBeInTheDocument();
  });

  it('updates input value on change', () => {
    const { getByTestId } = render(<WeatherSearchComponent />);
    const input = getByTestId('search-input');
    fireEvent.change(input, { target: { value: 'helsinki' } });
    expect(input.value).toBe('helsinki');
  });

  it('fetches weather information on button click and updates state', async () => {
    const mockData = { temperature: 25, condition: 'Sunny' };
    const mockResponse = {
      ok: true,
      json: jest.fn().mockResolvedValue(mockData),
    };
    jest.spyOn(global, 'fetch').mockResolvedValueOnce(mockResponse);

    const { getByTestId } = render(<WeatherSearchComponent />);
    const input = getByTestId('search-input');
    const button = getByTestId('search-button');

    fireEvent.change(input, { target: { value: 'helsinki' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(global.fetch).toHaveBeenCalledWith('http://localhost:8080/helsinki');
      expect(global.fetch).toHaveBeenCalledTimes(1);
      expect(mockResponse.json).toHaveBeenCalledTimes(1);
      expect(mockResponse.json).toHaveBeenCalledWith();
    });
  });

  it('handles error during weather information fetching', async () => {
    const mockErrorResponse = { ok: false, status: 404 };
    jest.spyOn(global, 'fetch').mockResolvedValueOnce(mockErrorResponse);
    console.error = jest.fn();

    const { getByTestId } = render(<WeatherSearchComponent />);
    const input = getByTestId('search-input');
    const button = getByTestId('search-button');

    fireEvent.change(input, { target: { value: 'invalidcity' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(global.fetch).toHaveBeenCalledWith('http://localhost:8080/invalidcity');
      expect(global.fetch).toHaveBeenCalledTimes(1);
      expect(console.error).toHaveBeenCalledWith('Error fetching data:', expect.any(Error));
    });
  });
});
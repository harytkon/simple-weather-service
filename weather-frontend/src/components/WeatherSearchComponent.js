import React, { useState } from 'react';

const WeatherSearchComponent = () => {
  const [inputValue, setInputValue] = useState('');
  const [responseData, setResponseData] = useState(null);

  const fetchData = async () => {
    try {
      const response = await fetch(`http://localhost:8080/weather/${inputValue}`);
      if (!response.ok) {
        throw new Error(`HTTP error: ${response.status}`);
      }
      const data = await response.json();
      setResponseData(data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <div>
      <input
        type="text"
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
        placeholder="Enter your query"
        data-testid="search-input"
      />
      <button onClick={fetchData} data-testid="search-button">Search</button>

      {responseData && (
        <div>
          <h2>Response:</h2>
          <pre>{JSON.stringify(responseData)}</pre>
        </div>
      )}
    </div>
  );
};

export default WeatherSearchComponent;
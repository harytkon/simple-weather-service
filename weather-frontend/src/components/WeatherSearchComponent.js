import React, { useState } from 'react';
import axios from 'axios';

const WeatherSearchComponent = () => {
  const [inputValue, setInputValue] = useState('');
  const [responseData, setResponseData] = useState(null);

  const fetchData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/${inputValue}`);
      setResponseData(response.data);
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
      />
      <button onClick={fetchData}>Search</button>

      {responseData && (
        <div>
          <h2>Response:</h2>
          <pre>{JSON.stringify(responseData, null, 2)}</pre>
        </div>
      )}
    </div>
  );
};

export default WeatherSearchComponent;
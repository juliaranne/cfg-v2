// import axios from 'axios';
import { useEffect, useState, useCallback } from 'react';

const useFetch = (query) => {
    console.log(query)
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

    const fetchData = useCallback(async () => {
        try {
            fetch(`http://localhost:5051/graphql`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ query }),
            })
            .then((res) => res.json())
            .then((result) => setData(result.data));
        } catch (e) {
            setError(e)
            console.log('error')
        }
    },[query])

    useEffect(() => {
        fetchData();
    }, [query, fetchData])
  

  return { data, error };
};

export default useFetch;

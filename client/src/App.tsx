import React, {useEffect, useState} from 'react';
import {Container, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material";

import WeatherTable from "./components/WeatherTable";
import CityWeatherResponse from "./types/CityWeatherResponse";
import TablePagination from "./components/TablePagination";

const initialState = {
    cities: [],
    pagination: {
        page: 0,
        pageSize: 15,
        totalCount: 0,
        totalPages: 0,
        last: false
    }
};

function App() {
    const [state, setState] = useState<CityWeatherResponse>(initialState);
    const [sort, setSort] = useState({field: "name", direction: "ASC"})
    // const baseUrl = process.env.SERVER_URL;
    const baseURL = "http://localhost:8080"
    console.log("baseUrl", baseURL)

    const fetchData = (page: number, pageSize: number, sortBy: string, sortDir: string) => {
        fetch(`${baseURL}/cities/weathers?page=${page}&pageSize=${pageSize}&sort=${sortBy},${sortDir}`)
            .then(res => res.json())
            .then(data => setState(data))
            .catch(err => console.error(err));
    }

    useEffect(() => {
        fetchData(state.pagination.page, state.pagination.pageSize, sort.field, sort.direction);
    }, [])

    return (
        <Container maxWidth="md" style={{paddingTop: "50px"}}>
            <div style={{display: "flex", justifyContent: "flex-end", marginBlock: "24px"}}>
                <FormControl style={{marginRight: '8px', width: '120px'}}>
                    <InputLabel id="sort-by-label">Sort</InputLabel>
                    <Select
                        labelId="sort-by-label"
                        id="sort-by-select"
                        value={sort.field}
                        label="Sort By"
                        name="sortBy"
                        onChange={(event: SelectChangeEvent) => {
                            setSort({...sort, field: event.target.value})
                            fetchData(
                                state.pagination.page,
                                state.pagination.pageSize,
                                event.target.value,
                                sort.direction
                            );
                        }}
                    >
                        <MenuItem value="name">Name</MenuItem>
                        <MenuItem value="temperature">Temperature</MenuItem>
                    </Select>
                </FormControl>

                <FormControl>
                    <InputLabel id="sort-dir-label">Direction</InputLabel>
                    <Select
                        labelId="sort-dir-label"
                        id="sort-dir-select"
                        value={sort.direction}
                        label="Sort dir"
                        name="sortDir"
                        onChange={(event: SelectChangeEvent) => {
                            setSort({...sort, direction: event.target.value})
                            fetchData(
                                state.pagination.page,
                                state.pagination.pageSize,
                                sort.field,
                                event.target.value
                            );
                        }}
                    >
                        <MenuItem value="ASC">ASC</MenuItem>
                        <MenuItem value="DESC">DESC</MenuItem>
                    </Select>
                </FormControl>
            </div>
            <WeatherTable cities={state.cities}/>
            {!!state?.pagination &&
                <TablePagination
                    pagination={state?.pagination}
                    pageSizeOnChange={(pageSize: number) =>
                        fetchData(
                            state.pagination.page,
                            pageSize,
                            sort.field,
                            sort.direction
                        )
                    }
                    pageOnChange={(page: number) =>
                        fetchData(
                            page,
                            state.pagination.pageSize,
                            sort.field,
                            sort.direction
                        )
                    }
                />}
        </Container>
    );
}

export default App;

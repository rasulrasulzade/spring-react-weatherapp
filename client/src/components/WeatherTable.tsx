import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import CityWeather from "../types/CItyWeather";

const WeatherTable = ({cities}: { cities: CityWeather[] }) => {

    return (
        <TableContainer component={Paper}>
            <Table sx={{minWidth: 650}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>#</TableCell>
                        <TableCell>City</TableCell>
                        <TableCell align="right">Temperature</TableCell>
                        <TableCell align="right">Condition</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {cities?.map((row: CityWeather, index: number) => (
                        <TableRow
                            key={row.id}
                            sx={{'&:last-child td, &:last-child th': {border: 0}}}
                        >

                            <TableCell component="th" scope="row">
                                {index + 1}
                            </TableCell>
                            <TableCell component="th" scope="row">
                                {row.name}
                            </TableCell>
                            <TableCell align="right">{row.temperature}</TableCell>
                            <TableCell align="right">{row.condition}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default WeatherTable;
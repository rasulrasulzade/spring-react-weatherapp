import * as React from "react";
import {MenuItem, Pagination, Select, Stack} from "@mui/material";

type PaginationPropsType = {
    pagination: {
        page: number,
        pageSize: number;
        totalCount: number;
        totalPages: number;
        last: boolean;
    },
    pageSizeOnChange: (pageSize: number) => void,
    pageOnChange: (page: number) => void,

}

const TablePagination = ({pagination, pageSizeOnChange, pageOnChange}: PaginationPropsType) => {
    return <div style={{
        display: 'flex', justifyContent: 'space-between',
        alignItems: 'center', padding: '16px'
    }}>
        <Select
            style={{width: '8ch', height: '32px'}}
            name="pageSize"
            value={pagination?.pageSize || 0}
            onChange={(event) =>
                pageSizeOnChange(Number(event.target.value))}
        >
            <MenuItem value={5}>5</MenuItem>
            <MenuItem value={15}>15</MenuItem>
            <MenuItem value={20}>25</MenuItem>
        </Select>
        {
            pagination.totalPages > 1 &&
            <Stack spacing={2} style={{padding: '16px'}}>
                <Pagination
                    count={pagination.totalPages}
                    page={pagination.page}
                    variant="outlined"
                    shape="rounded"
                    color="primary"
                    onChange={(event, page) => pageOnChange(page)}
                />
            </Stack>
        }
    </div>
}

export default TablePagination;
import CityWeather from "./CItyWeather";

type CityWeatherResponse = {
    cities: CityWeather[];
    pagination: {
        page: number,
        pageSize: number;
        totalCount: number;
        totalPages: number;
        last: boolean;
    }
};
export default CityWeatherResponse;
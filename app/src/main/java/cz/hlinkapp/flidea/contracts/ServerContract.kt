package cz.hlinkapp.flidea.contracts

import cz.hlinkapp.flidea.model.SearchFilters
import java.util.*
import kotlin.collections.HashMap

/**
 * An interface containing several constants and utility functions for communicating with the server.
 */
interface ServerContract {

    companion object {

        //Base
        const val PROTOCOL = "https://"
        const val BASE_URL = "${PROTOCOL}api.skypicker.com/"
        const val FLIGHTS = "flights"
        const val LOCATIONS = "locations"

        //Images
        const val IMAGES_LOCATIONS_URL = "${PROTOCOL}images.kiwi.com/photos/600x330/"
        const val IMAGES_AIRLINE_LOGOS_URL = "${PROTOCOL}images.kiwi.com/airlines/64x64/"
        const val IMAGE_FILE_FORMAT_LOCATIONS = ".jpg"
        const val IMAGE_FILE_FORMAT_AIRLINE_LOGOS = ".png"

        //Flights query parameters
        const val QP_FLY_FROM = "fly_from"
        const val QP_FLY_TO = "fly_to"
        const val QP_DATE_FROM = "date_from"
        const val QP_DATE_TO = "date_to"
        const val QP_RETURN_FROM = "return_from"
        const val QP_RETURN_TO = "return_to"
        const val QP_NIGHTS_IN_DST_FROM = "nights_in_dst_from"
        const val QP_NIGHTS_IN_DST_TO = "nights_in_dst_to"
        const val QP_FLIGHT_TYPE = "flight_type"
        const val QP_PASSENGERS = "adults"
        const val QP_PARTNER = "partner"
        const val QP_CURRENCY = "curr"
        const val QP_LOCALE = "locale"
        const val QP_LIMIT = "limit"
        const val QP_SORT = "sort"
        const val QP_ASCENDING = "asc"
        const val QP_ONE_FOR_CITY= "one_for_city"

        //Location search query params
        const val QP_TERM = "term"
        const val QP_LOCATION_TYPES = "location_types"
        const val QP_ACTIVE_ONLY = "active_only"

        //Flights constant values
        const val VAL_PARTNER = "picky"
        const val VAL_ONE_FOR_CITY = "1"
        const val VAL_FLIGHTS_TYPE = "round"
        const val VAL_SORT_BY_QUALITY = "quality"

        //Location search constant values
        const val VAL_LOCATION_TYPES = "airport"
        const val VAL_SORT_BY_NAME = "name"

        //Default SearchFilters
        //--see SearchFilters and SearchFilterFragment
        const val DEFAULT_AIRPORT_CODE = "PRG"
        const val DEFAULT_AIRPORT_NAME = "Václav Havel Airport Prague"

        //Choices
        val CURRENCIES = arrayOf("AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTC","BTN","BWP","BYR","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EEK","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MTL","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","QUN","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XAG","XAU","XCD","XDR","XOF","XPD","XPF","XPT","YER","ZAR","ZMK","ZMW","ZWL")
        val LANGUAGES = arrayOf("ae","ag","ar","at","au","be","bg","bh","br","by","ca","ca-fr","ch","cl","cn","co","ct","cz","da","de","dk","ec","ee","el","en","es","fi","fr","gb","gr","hk","hr","hu","id","ie","il","in","is","it","ja","jo","jp","ko","kr","kw","kz","lt","mx","my","nl","no","nz","om","pe","ph","pl","pt","qa","ro","rs","ru","sa","se","sg","sk","sr","sv","th","tr","tw","ua","uk","us","vn","za")

        //Image URL functions

        /**
         * Returns a url string with an airline logo of the airline with the provided [code].
         */
        fun createAirlineLogoImageUrl(code: String) = "$IMAGES_AIRLINE_LOGOS_URL$code$IMAGE_FILE_FORMAT_AIRLINE_LOGOS"

        /**
         * Returns a url string with a location image of the location with the provided [mapId].
         */
        fun createLocationImageUrl(mapId: String) = "$IMAGES_LOCATIONS_URL$mapId$IMAGE_FILE_FORMAT_LOCATIONS"

        /**
         * Creates map of query params for a location search.
         * Includes the [query] and default values.
         */
        fun createLocationSearchQueryParams(query: String) : HashMap<String,Any> {
            val map = HashMap<String,Any>()
            map[QP_TERM] = query
            map[QP_LOCATION_TYPES] = VAL_LOCATION_TYPES
            map[QP_LIMIT] = 20
            map[QP_ACTIVE_ONLY] = true
            map[QP_SORT] = VAL_SORT_BY_NAME
            return map
        }

        /**
         * Creates map of query params for flights search.
         * @param searchFilters Current search filters.
         */
        fun createFlightQueryParams(searchFilters: SearchFilters) : HashMap<String,Any> {
            val map = HashMap<String, Any>()
            val cal = Calendar.getInstance()
            map[QP_FLY_FROM] = searchFilters.airport.id
            map[QP_DATE_FROM] = "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"
            cal.add(Calendar.DAY_OF_MONTH,14)
            map[QP_DATE_TO] = "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"
            map[QP_NIGHTS_IN_DST_FROM] = 1
            map[QP_NIGHTS_IN_DST_TO] = 14
            map[QP_FLIGHT_TYPE] = VAL_FLIGHTS_TYPE
            map[QP_PASSENGERS] = searchFilters.passengers
            map[QP_PARTNER] = VAL_PARTNER
            map[QP_LIMIT] = 150
            map[QP_SORT] = VAL_SORT_BY_QUALITY
            map[QP_ASCENDING] = 0
            map[QP_ONE_FOR_CITY] = VAL_ONE_FOR_CITY
            return map
        }
    }
}
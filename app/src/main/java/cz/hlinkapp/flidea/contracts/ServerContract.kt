package cz.hlinkapp.flidea.contracts

interface ServerContract {

    companion object {
        const val PROTOCOL = "https://"
        const val BASE_URL = "${PROTOCOL}api.skypicker.com"
        const val FLIGHTS = "${BASE_URL}/flights"

        //Query parameters
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

        //Constant values
        const val VAL_FLY_TO = "anywhere"
        const val VAL_PARTNER = "picky"
        const val VAL_ONE_FOR_CITY = "1"

        //Choices
        val CURRENCIES = arrayOf("AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTC","BTN","BWP","BYR","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EEK","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MTL","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","QUN","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XAG","XAU","XCD","XDR","XOF","XPD","XPF","XPT","YER","ZAR","ZMK","ZMW","ZWL")
        val LANGUAGES = arrayOf("ae","ag","ar","at","au","be","bg","bh","br","by","ca","ca-fr","ch","cl","cn","co","ct","cz","da","de","dk","ec","ee","el","en","es","fi","fr","gb","gr","hk","hr","hu","id","ie","il","in","is","it","ja","jo","jp","ko","kr","kw","kz","lt","mx","my","nl","no","nz","om","pe","ph","pl","pt","qa","ro","rs","ru","sa","se","sg","sk","sr","sv","th","tr","tw","ua","uk","us","vn","za")

    }
}
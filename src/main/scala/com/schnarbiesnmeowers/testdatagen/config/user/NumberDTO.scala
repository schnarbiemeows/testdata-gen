package com.schnarbiesnmeowers.testdatagen.config.user

class NumberDTO(
                 val customerid: Int,
                 val dataconfigname: String,
                 val order: Int,
                 val canbenull: Boolean,
                 val canbeblank: Boolean,
                 val canbeinvalid: Boolean,
                 val nullpercent: Int,
                 val blankpercent: Int,
                 val invalidpercent: Int
               ) extends BaseTypeDTO {

}

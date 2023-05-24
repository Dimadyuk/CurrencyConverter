package com.example.currencyconverter.data

import com.example.currencyconverter.data.models.UserDbModel
import com.example.currencyconverter.domain.User

class UserMapper {

    fun mapUserToDb(user: User) = UserDbModel(
        login = user.login,
        password = user.password,
        name = user.name,
        surname = user.surname
    )

    fun mapDbToUser(userDbModel: UserDbModel) = User(
        login = userDbModel.login,
        password = userDbModel.password,
        name = userDbModel.name,
        surname = userDbModel.surname
    )


}

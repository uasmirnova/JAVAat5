package ru.netology.testinfo;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    public static Info user(String locale) {
        return new Info(userCity(locale), userName(locale), userPhone(locale));
    }

    public static String userCity(String locale) {
        String[] cities = new String[]{"Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста", "Черкесск",
                "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл",
                "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск",
                "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск",
                "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров",
                "Кострома", "Курган", "Курск", "Липецк", "Магадан", "Мурманск", "Нижний Новгород", "Великий Новгород", "Новосибирск",
                "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск",
                "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль",
                "Москва", "Санкт-Петербург", "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард", "Майкоп"};
        Faker faker = new Faker(new Locale(locale));
        return cities[faker.number().numberBetween(0, cities.length - 1)];
    }

    public static String userName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String userPhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }
}

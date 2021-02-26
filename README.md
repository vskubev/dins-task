# API cars

API with cars characteristics

## Run

1. Download docker desktop for your OS

   ```
   https://www.docker.com/products/docker-desktop
   ```

1. Run command from root of this project in terminal

   ```bash
   docker-compose up -d
   ```

_If you get an error **Filesharing has been cancelled** on Windows – you need to add project directory to Resources -> File Sharing in Docker Desktop. (see: https://stackoverflow.com/questions/60754297/docker-compose-failed-to-build-filesharing-has-been-cancelled)_

1. You can check running containers with command

   ```bash
   docker ps
   ```   
1. API located at
   
    ```
    http://localhost:8084
    ```
   
1. To stop all services use

   ```bash
   docker-compose stop
   ```         

# Тестовое задание

Дано несовершенное API со следующеми эндпоинтами (инструкцию по запуску см. выше):
* **GET** /api/v2/cars
 ```json
[
  {
    "id": 27,
    "segment": "C-segment",
    "brand": "Volkswagen",
    "model": "Golf",
    "generation": "Mk8",
    "modification": "1.5 TSI"
  },
  {
    "id": 28,
    "segment": "C-segment",
    "brand": "Volkswagen",
    "model": "Golf",
    "generation": "Mk7",
    "modification": "2.0 TDI 4Motion"
  },
  ...
]
```
*  **GET** /api/v2/cars/{carId}
```json
{
  "id": 27,
  "segment": "C-segment",
  "brand": "Volkswagen",
  "model": "Golf",
  "generation": "Mk8",
  "modification": "1.5 TSI",
  "year_range": "2019-present",
  "engine_type": "GASOLINE",
  "engine_cylinders": "L4",
  "engine_displacement": 1498,
  "engine_horsepower": 150,
  "gearbox": "ROBOTIC",
  "wheel_drive": "FWD",
  "body_length": 4284,
  "body_width": 1789,
  "body_height": 1491,
  "body_style": "Hatchback",
  "acceleration": 8.5,
  "max_speed": 224
}
```
*  **GET** /api/v2/countries
```json
[
  {
    "id": 1,
    "title": "Czech Republic",
    "brands": [
      "Skoda"
    ]
  },
  {
    "id": 2,
    "title": "England",
    "brands": [
      "Bentley",
      "Jaguar"
    ]
  },
...
]
```

Пусть характеристики двигателя это:
* engine_type
* engine_cylinders
* engine_displacement
* engine_horsepower,

а характеристики кузова:
* body_length
* body_width
* body_height
* body_style

Необходимо написать RESTful приложение на языке Java, используя этот проект в качестве заготовки, которое:
1. При запуске парсит данные из **API cars characteristics**, сохраняет данные в памяти таким образом, что они гарантировано будут храниться в единственном экземпляре некоторой структуры. В качестве дополнительного плюса будет расценено, если парсинг будет производиться с эндпоинтов, которые оканчиваются на ```/paged``` (там используется Spring Pageable https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html).
1. Содержит эндпоинт, выдающий массив автомобилей в формате JSON.
    ```
    GET /api/cars
    ```
    По каждому автомобилю в ответе необходимо вернуть следующие поля: 
    * идентификатор автомобиля (**id**)
    * название сегмента (**segment**)
    * название бренда (**brand**)
    * название модели (**model**)
    * наименование страны производителя (**country**)
    * наименование поколения модели (**generation**)
    * наименование модификации (**modification**).

    В запросе могут присутствовать следующие параметры (если параметр указан не верно, приложение не должно "падать" или выдавать 500 код, должен возвращаться корректный ответ, например HTTP CODE 404, что ничего не найдено):
    1. **brand**: если он задан, то должны выдаваться только автомобили, производимые указанным брендом.
    1. **segment**: если он задан, то должны выдаваться только автомобили, принадлежащие указанному сегменту.
    1. **minEngineDisplacement**: если он задан, то должны выдаваться только автомобили, у которых рабочий объем двигателя выше или равен указанному значению (в случае неверного параметра, например если это не число, выдавать HTTP CODE 400).
    1. **minEngineHorsepower**: если он задан, то должны выдаваться только автомобили, мощность которых выше или равна указанному значению (в случае неверного параметра, например если это не число, выдавать HTTP CODE 400).
    1. **minMaxSpeed**: если он задан, то должны выдаваться только автомобили, максимальная скорость которых выше или равна указанному значению (в случае неверного параметра, например, если это не число, выдавать HTTP CODE 400).
    1. **search**: если он задан, то проводится поиск по следующим полям: по названию модели, поколению и названию модификации.
    1. **isFull**: принимает true/false, если он true, то по автомобилю должна выдаваться полная информация: должны быть добавлены характеристики двигателя и кузова в отдельные объекты **engine** и **body**.
    1. _(дополнительный плюс)_ **year**: если он задан, то выдаются только автомобили, которые выпускались в заданный год (в случае неверного параметра, например если это не число, выдавать HTTP CODE 400).
    1. _(дополнительный плюс)_ **bodyStyle**: если он задан, то выдаются только автомобили, у которых есть этот тип кузова.
    
    _Фильтрация по всем параметрам сразу не является обязательной, но будет рассматриваться как дополнительный плюс._
    
    Пример запроса:
    ```
    http://localhost:8081/api/cars?brand=Volkswagen&segment=E-segment&minEngineDisplacement=4.0&minEngineHorsepower=250&minMaxSpeed=200&search=5
    ```
    
1. Содержит эндпоинты со следующими словарями, **на основе только тех данных, которые получены из внешней API**:
   * всех возможных типов топлива
   ```
   GET /api/fuel-types
   ```
   * всех возможных типов кузова
   ```
   GET /api/body-styles
   ```
   * всех возможных типов двигателя 
   ```
   GET /api/engine-types
   ```
   * всех возможных типов привода 
   ```
   GET /api/wheel-drives
   ```
   * всех возможных типов коробок передач 
   ```
   GET /api/gearboxes
   ```   
1. Содержит эндпоинт, выдающий  значение **средней максимальной скорости** по бренду/модели (параметры в запросе **brand**/**model**). Если заданного бренда или модели нет – выдавать HTTP CODE 404. В случае если указаны одновременно оба параметра – выдавать HTTP CODE 400.
   ```
   GET /api/max-speed
   ```
   Пример запроса:
   ```
   http://localhost:8081/api/max-speed?brand=BMW
   ```

Для проверки проект должен быть выложен в открытый доступ на какой-либо платформе (github, gitlab и т.д.).
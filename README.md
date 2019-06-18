# Searching approach explanation

* On the first launch of the application MainPresenter loads cities from CitiesRepository
* CitiesRepository: 
   > 1. gets cities data from assets file. 
   > 2. sorts them (in cities names order than countries names order). 
   > 3. creates CitiesData with cities itself and associative list of cities names in lowercase.
   > 4. transfers CitiesData to MainPresenter.
* MainPresenter use SearchUtils to filter cities.
* Search utils works with CitiesData.
* SearchUtils:
    > 1. finds the index of the leftmost element  in cities list matching the prefix by binary search. Complexity is log(N) 
    > 2. finds the index of the rightmost element in cities list matching the prefix by binary search. Complexity is log(N)
    > 3. creates sublist from Cities limited by left and right indexes and returns it as a result. 

var app = angular.module('MyBoard');
app.factory('ItemService', function(){

  var makeNewItem = function(){
    return {
      title:'',
      text:'',
      state: 0
    };
  };

  return {
    makeNewItem: makeNewItem
  }

});

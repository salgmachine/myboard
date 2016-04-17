var app = angular.module('MyBoard');
app.factory('Storage', function(){


  var get = function(key){
    return localStorage.getItem(key);
  };

  var set = function(key, value){
    return localStorage.setItem(key, JSON.stringify(value));
  };

  var remove = function(key){
    return localStorage.removeItem(key);
  };

  var service = {
    get: get,
    set: set,
    remove: remove
  };

  return service;

});

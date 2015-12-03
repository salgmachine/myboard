var app = angular.module('MyBoard', ['ngResource']);
app.config(function($httpProvider){
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
  $httpProvider.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8';
  $httpProvider.defaults.headers.common['Accept'] = 'application/json;charset=UTF-8';
  $httpProvider.defaults.headers.get = {'Content-Type' : 'application/json;charset=UTF-8', 'Accept' :'application/json;charset=UTF-8' };
  $httpProvider.interceptors.push('myInterceptor');
});
app.factory('myInterceptor', function() {
  var contentTypeInjector = {
    request: function(config) {
      config.headers['Content-Type'] = 'application/json;charset=UTF-8';
      return config;
    }
  };
  return contentTypeInjector;
});

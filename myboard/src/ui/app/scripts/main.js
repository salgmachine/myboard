var app = angular.module('MyBoard', ['ngResource', 'cfp.hotkeys', 'textAngular', 'pascalprecht.translate', 'ngDraggable']);
app.config(function($translateProvider, $provide, $httpProvider){


  $translateProvider.useSanitizeValueStrategy('sanitize');
  $translateProvider.useStaticFilesLoader({
    prefix: 'i18n/',
    suffix: '.json'
  });
  $translateProvider.preferredLanguage('en_us');

  delete $httpProvider.defaults.headers.common['X-Requested-With'];
  $httpProvider.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8';
  $httpProvider.defaults.headers.common['Accept'] = 'application/json;charset=UTF-8';
  $httpProvider.defaults.headers.get = {'Content-Type' : 'application/json;charset=UTF-8', 'Accept' :'application/json;charset=UTF-8' };
  $httpProvider.interceptors.push('myInterceptor');

  $provide.decorator('taOptions', ['$timeout', 'taRegisterTool', '$delegate', function($timeout, taRegisterTool, taOptions) { // $delegate is the taOptions we are decorating
    taOptions.toolbar = [
      [ 'h2', 'h4', 'h6'],
      ['p', 'pre', 'quote'],
      ['bold', 'italics', 'strikeThrough'],
      ['ul', 'ol', 'clear'],
      [ 'indent', 'outdent'],
      ['html', 'insertImage','insertLink']
    ];

    return taOptions;
  }]);

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

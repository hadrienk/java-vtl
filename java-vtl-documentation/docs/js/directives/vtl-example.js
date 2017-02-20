/*global define*/
'use strict';

define(['angular',], function (angular) {
    var moduleName = 'vtl';
    angular.module(moduleName, [])
        .directive('vtlExample', function () {
            return {
                restrict: 'AE',
                scope: {},
                transclude: true,
                replace: true,
                template: '<div class="vtl-example" ng-transclude></div>'
            };
        });
    return moduleName;
});

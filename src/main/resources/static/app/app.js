(function(angular) {

	var app = angular.module("myApp", [ "FileManagerApp" ]);

	var fileManagerConfigProvider = null;

	app.config(["fileManagerConfigProvider", function (config) {
		fileManagerConfigProvider = config;
	}]);

	app.run([ "$rootScope", "$http", "apiHandler", "$q", "Upload",  function($rootScope, $http, apiHandler, $q, Upload) {
		$rootScope.isLoading = true;
		$http.get("/configuration").then(function(response){
			var defaults = fileManagerConfigProvider.$get();
			fileManagerConfigProvider.set({
					appName : 'Spring-filemanager',
					listUrl : 'action/list',
					uploadUrl : 'action/upload',
					renameUrl : 'action/rename',
					copyUrl : 'action/copy',
					moveUrl : 'action/move',
					removeUrl : 'action/remove',
					editUrl : 'action/edit',
					getContentUrl : 'action/get-content',
					createFolderUrl : 'action/create-folder',
					downloadFileUrl : 'action/download',
					downloadMultipleUrl : 'action/download-multiple',
					compressUrl : 'action/compress',
					extractUrl : 'action/extract',
					permissionsUrl : 'action/permission',
					basePath : '/',
					allowedActions : response.data.allowedActions
			});
		}).finally(function(){
			$rootScope.isLoading = false;
		});


		apiHandler.prototype.upload = function(apiUrl, destination, files) {
            var self = this;
            var deferred = $q.defer();
            self.inprocess = true;
            self.progress = 0;
            self.error = '';

            if (files && files.length) {
                Upload.upload({
                    url: apiUrl,
    				data: {
    					destination: destination,
    					file: files
    				},
					arrayKey: '' //Fix to work with Spring MVC Rest,
                }).then(function (data) {
                    self.deferredHandler(data.data, deferred, data.status);
                }, function (data) {
                    self.deferredHandler(data.data, deferred, data.status, 'Unknown error uploading files');
                }, function (evt) {
                    self.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total)) - 1;
                })['finally'](function() {
                    self.inprocess = false;
                    self.progress = 0;
                });
            }

            return deferred.promise;
        };
	}]);



}(angular));
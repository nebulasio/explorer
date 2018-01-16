<?php

/**
 * 请先运行:php server/http_server.php
 * @author xmc
 */
class Httpindex
{
    static public  $httpdefined;
    private function __construct()
    {
        /*
         *---------------------------------------------------------------
         * APPLICATION ENVIRONMENT
         *---------------------------------------------------------------
         *
         * You can load different configurations depending on your
         * current environment. Setting the environment also influences
         * things like logging and error reporting.
         *
         * This can be set to anything, but default usage is:
         *
         *     development
         *     testing
         *     production
         *
         * NOTE: If you change these, also change the error_reporting() code below
         */
        define('ENVIRONMENT', isset($_SERVER['CI_ENV']) ? $_SERVER['CI_ENV'] : 'development');
        
        /*
         *---------------------------------------------------------------
         * ERROR REPORTING
         *---------------------------------------------------------------
         *
         * Different environments will require different levels of error reporting.
         * By default development will show errors but testing and live will hide them.
         */
        switch (ENVIRONMENT)
        {
            case 'development':
                error_reporting(-1);
                ini_set('display_errors', 1);
                break;
                
            case 'testing':
            case 'production':
                ini_set('display_errors', 0);
                if (version_compare(PHP_VERSION, '5.3', '>='))
                {
                    error_reporting(E_ALL & ~E_NOTICE & ~E_DEPRECATED & ~E_STRICT & ~E_USER_NOTICE & ~E_USER_DEPRECATED);
                }
                else
                {
                    error_reporting(E_ALL & ~E_NOTICE & ~E_STRICT & ~E_USER_NOTICE);
                }
                break;
                
            default:
                header('HTTP/1.1 503 Service Unavailable.', TRUE, 503);
                echo 'The application environment is not set correctly.';
                exit(1); // EXIT_ERROR
        }
        
        
        /*
         *---------------------------------------------------------------
         * SYSTEM FOLDER NAME
         *---------------------------------------------------------------
         */
        $system_path = '/home/anman/nebulas/workspace/statuspage/core/system';
        /*
         *---------------------------------------------------------------
         * APPLICATION FOLDER NAME
         *---------------------------------------------------------------
         */
        $application_folder = '/home/anman/nebulas/workspace/statuspage/core/application';
        
        /*
         *---------------------------------------------------------------
         *VIEVER FOLDER NAME
         *---------------------------------------------------------------
         */
        $view_folder = '/home/anman/nebulas/workspace/statuspage/core/application/views';
        /*
         * ---------------------------------------------------------------
         *  Resolve the system path for increased reliability
         * ---------------------------------------------------------------
         */
        // Set the current directory correctly for CLI requests
        if (defined('STDIN'))
        {
            chdir(dirname(__FILE__));
        }
        
        if (($_temp = realpath($system_path)) !== FALSE)
        {
            $system_path = $_temp.DIRECTORY_SEPARATOR;
        }
        else
        {
            // Ensure there's a trailing slash
            $system_path = strtr(
                rtrim($system_path, '/\\'),
                '/\\',
                DIRECTORY_SEPARATOR.DIRECTORY_SEPARATOR
                ).DIRECTORY_SEPARATOR;
        }
        
        // Is the system path correct?
        if ( ! is_dir($system_path))
        {
            header('HTTP/1.1 503 Service Unavailable.', TRUE, 503);
            echo 'Your system folder path does not appear to be set correctly. Please open the following file and correct this: '.pathinfo(__FILE__, PATHINFO_BASENAME);
            exit(3); // EXIT_CONFIG
        }
        /*
         * -------------------------------------------------------------------
         *  Now that we know the path, set the main path constants
         * -------------------------------------------------------------------
         */
        // The name of THIS file
        define('SELF', pathinfo(__FILE__, PATHINFO_BASENAME));
        
        // Path to the system directory
        define('BASEPATH', $system_path);
        
        // Path to the front controller (this file) directory
        define('FCPATH', dirname(__FILE__).DIRECTORY_SEPARATOR);
        
        // Name of the "system" directory
        define('SYSDIR', basename(BASEPATH));
        
        // The path to the "application" directory
        if (is_dir($application_folder))
        {
            if (($_temp = realpath($application_folder)) !== FALSE)
            {
                $application_folder = $_temp;
            }
            else
            {
                $application_folder = strtr(
                    rtrim($application_folder, '/\\'),
                    '/\\',
                    DIRECTORY_SEPARATOR.DIRECTORY_SEPARATOR
                    );
            }
        }
        elseif (is_dir(BASEPATH.$application_folder.DIRECTORY_SEPARATOR))
        {
            $application_folder = BASEPATH.strtr(
                trim($application_folder, '/\\'),
                '/\\',
                DIRECTORY_SEPARATOR.DIRECTORY_SEPARATOR
                );
        }
        else
        {
            header('HTTP/1.1 503 Service Unavailable.', TRUE, 503);
            echo 'Your application folder path does not appear to be set correctly. Please open the following file and correct this: '.SELF;
            exit(3); // EXIT_CONFIG
        }
        
        define('APPPATH', $application_folder.DIRECTORY_SEPARATOR);
        
        // The path to the "views" directory
        if ( ! isset($view_folder[0]) && is_dir(APPPATH.'views'.DIRECTORY_SEPARATOR))
        {
            $view_folder = APPPATH.'views';
        }
        elseif (is_dir($view_folder))
        {
            if (($_temp = realpath($view_folder)) !== FALSE)
            {
                $view_folder = $_temp;
            }
            else
            {
                $view_folder = strtr(
                    rtrim($view_folder, '/\\'),
                    '/\\',
                    DIRECTORY_SEPARATOR.DIRECTORY_SEPARATOR
                    );
            }
        }
        elseif (is_dir(APPPATH.$view_folder.DIRECTORY_SEPARATOR))
        {
            $view_folder = APPPATH.strtr(
                trim($view_folder, '/\\'),
                '/\\',
                DIRECTORY_SEPARATOR.DIRECTORY_SEPARATOR
                );
        }
        else
        {
            header('HTTP/1.1 503 Service Unavailable.', TRUE, 503);
            echo 'Your view folder path does not appear to be set correctly. Please open the following file and correct this: '.SELF;
            exit(3); // EXIT_CONFIG
        }
        
        define('VIEWPATH', $view_folder.DIRECTORY_SEPARATOR);
        
        
        
        
        
    }
    
    public static function getInstance($request, $response)
    {
        if (!self::$httpdefined) {
            $httpdefined = new self();
            self::$httpdefined = $httpdefined;
        }
        /*
         * --------------------------------------------------------------------
         * LOAD THE BOOTSTRAP FILE
         * --------------------------------------------------------------------
         * And away we go...
         */
        $_GET = $_POST = array();
        $GLOBALS['ISEND'] = FALSE;
        $GLOBALS['REQUEST'] = $request;
        $GLOBALS['RESPONSE'] = $response;
        if (isset($request->header)) {
            $_SERVER['server_head'] = $request->header;
        }
        if (isset($request->get)) {
            $_GET = $request->get;
        }
        if (isset($request->post)) {
            $_POST = $request->post;
        }
        include BASEPATH.'core/CodeIgniter.php';
    }
    
    public function __destruct()
    {
        self::$httpdefined=false;
    }
}
/* End of file index.php */
/* Location: ./index.php */
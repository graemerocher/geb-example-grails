grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir="target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.work.dir = "target"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver="maven"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
	
	def gebVersion = "0.7.0"
	def seleniumVersion = "2.21.0"
	
	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenLocal()
		mavenCentral()
		
		// For Geb snapshot
		mavenRepo "https://nexus.codehaus.org/content/repositories/snapshots"
	}
	dependencies {
		test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
			exclude "xml-apis"
		}
		test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
		test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")
		test "org.codehaus.geb:geb-spock:$gebVersion"
		test "org.codehaus.geb:geb-junit4:$gebVersion"

        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
	}
	plugins {
        runtime ":hibernate:3.6.10.1" // or ":hibernate4:4.1.11.1"
        runtime ":jquery:1.10.2"
        runtime ":resources:1.1.6"

        build ":tomcat:7.0.42"
		
		test ":geb:0.9.1"
		

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"
	}
}
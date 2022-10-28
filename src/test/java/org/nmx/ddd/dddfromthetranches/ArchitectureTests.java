package org.nmx.ddd.dddfromthetranches;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;

public class ArchitectureTests {

	private static final String BOUNDARY = "boundary";
	private static final String DOMAIN = "domain";
	private static final String INFRASTRUCTURE = "infrastructure";

	// in domain
	private static final String APPLICATION = "application";
	private static final String MODEL = "model";
	private static final String PORT = "ports";
	
	
	
	private static JavaClasses productionClasses = new ClassFileImporter()
			.withImportOption(new DoNotIncludeTests())
			.importPackages("org.nmx.ddd.dddfromthetranches");

			
	@Test
	void domainDoesNotDependOnBoundary() throws Exception {
        noClasses().that().resideInAPackage(".." + DOMAIN + "..")
	    .should().dependOnClassesThat().resideInAPackage(".." + BOUNDARY + "..").allowEmptyShould(true).check(productionClasses);
	}

	@Test
	void domainDoesNotDependOnInfrastructure() throws Exception {
        noClasses().that().resideInAPackage(".." + DOMAIN + "..")
	    		.should().dependOnClassesThat().resideInAPackage(".." + INFRASTRUCTURE + "..").allowEmptyShould(true).check(productionClasses);
	}

	@Test
	void domainModelsDoesNotDependOnDomainControllers() throws Exception {
        noClasses().that().resideInAPackage(".." + DOMAIN + "." + MODEL + "..")
	    		.should().dependOnClassesThat().resideInAPackage(".." + DOMAIN + "." + APPLICATION + "..").allowEmptyShould(true).check(productionClasses);
	}

	@Test
	void domainPortsShouldNotDependOnDomainControllers() throws Exception {
        noClasses().that().resideInAPackage(".." + DOMAIN + "." + PORT + "..")
	    		.should().dependOnClassesThat().resideInAPackage(".." + DOMAIN + "." + APPLICATION + "..").allowEmptyShould(true).check(productionClasses);
	}
	
	
	/*
	static List<String> subdomainsList = Arrays.asList(); 
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class Subdomains {
		@ParameterizedTest 
		@MethodSource("providesDomainsCouples")
		void subDomainsAreIsolatedFromOneAnother(String domainA, String domainB) throws Exception {
					
			noClasses().that().resideInAPackage(".." + DOMAIN + "."+domainA+"..")
			.should().dependOnClassesThat().resideInAPackage(".." + DOMAIN + "."+domainB+"." + PORT + "..")
			.allowEmptyShould(true).check(productionClasses);
		}
		
		@ParameterizedTest 
		@MethodSource("providesDomainsCouples")
		void subDomainDoesNotDependOnApplication(String domainA, String domainB) throws Exception {
			noClasses().that().resideInAPackage(".." + DOMAIN + "."+domainA+"..")
		    .should().dependOnClassesThat().resideInAPackage(".." + BOUNDARY + "..").allowEmptyShould(true).check(productionClasses);
		}
	
		@ParameterizedTest 
		@MethodSource("providesDomainsCouples")
		void subDomainDoesNotDependOnInfrastructure(String domainA, String domainB) throws Exception {
			noClasses().that().resideInAPackage(".." + DOMAIN + "."+domainA+"..")
		    		.should().dependOnClassesThat().resideInAPackage(".." + INFRASTRUCTURE + "..").allowEmptyShould(true).check(productionClasses);
		}

	 // renvoie tous les permutations de couples (domainA, domainB)
	 // Sauf les identiques du type (domainA, domainA)
		Stream<Arguments> providesDomainsCouples() {
			return (Stream<Arguments>)subdomainsList.stream()
					.flatMap((String str1) -> subdomainsList.stream().filter((String str2) -> !str1.contentEquals(str2)).map(str2 -> Arguments.of(str1, str2)))
					.collect(Collectors.toList())
					.stream();
		}
	}
	*/
}

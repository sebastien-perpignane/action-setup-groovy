# Setup Groovy

The setup-groovy action allows to bring Groovy in GitHub Actions runners. It relies on setup-java action to install Java and on SDKMAN to install the Groovy SDK.

# Usage:

  - `groovy-version`: The Groovy version that will be set up. If not specified, the latest stable version will be installed.
  - `java-version`: No mandatory but strongly recommended. Groovy needs Java. If specified, a JDK will be installed. Default distribution is "temurin". You can change it.
  If not specified, you'll rely on the JRE provided by your OS. If the OS does not provide JRE, Groovy won't work.
  - `java-distribution`: Default is temurin. You can change to microsoft, azul etc.

More information about the possible values for `java-version` and `java-distribution` at : [actions/setup-java repo](https://github.com/actions/setup-java) 

# Examples

We have a script named MyScript.groovy at the root of our repository. To run it in a workflow, with the latest stable Groovy version:

```yaml
steps:
    - uses: sebastien-perpignane/action-setup-groovy@v1
      with:
        java-version: '17' # It's not mandatory to select a java version but strongly recommended 

    - name: Run groovy script
      shell: bash
      run: groovy MyScript.groovy
```

To select a specific Groovy version:

```yaml
steps:
    - uses: sebastien-perpignane/action-setup-groovy@v1
      with:
        groovy-version: '4.0.12'
        java-version: '17' # It's not mandatory to select a java version but strongly recommended 

    - name: Run groovy script
      shell: bash
      run: groovy MyScript.groovy
```

It is also possible to compile your Groovy script:

```yaml
steps:
    - uses: sebastien-perpignane/action-setup-groovy@v1
      java-version: '17' # It's not mandatory to select a java version but strongly recommended 

    - name: Run groovy script
      shell: bash
      run: groovyc MyScript.groovy

    - name: Display result
      shell: bash
      run: ls .  # output: MyScript.groovy MyScript.class
```

# Next step

- Typescript implementation of the action
- Manage a cache for grape.
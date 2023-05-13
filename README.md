# Setup Groovy

The setup-groovy action allows to bring Groovy in GitHub Actions runners. It uses SDKMAN to install the Groovy SDK.

# Usage:

  - `groovy-version`: The Groovy version that will be be set up. If not specified, the latest stable version will be installed.

# Examples

We have a script named MyScript.groovy at the root of our repository. To run it in a workflow, with the latest stable Groovy version:

```yaml
steps:
    - uses: sebastien-perpignane/action-setup-groovy@v1

    - name: Run groovy script
      shell: bash
      run: groovy MyScript.groovy
```

To select a specific Groovy version:

```yaml
steps:
    - uses: sebastien-perpignane/action-setup-groovy@v1
      with:
        groovy-version: '4.0.11'

    - name: Run groovy script
      shell: bash
      run: groovy MyScript.groovy
```

It is also possible to compile your Groovy script:

```yaml
steps:
    - uses: sebastien-perpignane/action-setup-groovy@v1

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
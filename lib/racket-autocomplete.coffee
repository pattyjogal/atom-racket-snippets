#RacketAutocompleteView = require './racket-autocomplete-view'
{CompositeDisposable} = require 'atom'

lineText = ""
cursorPosn = null
#editor = atom.workspace.getActiveTextEditor()
#console.log editor
console.log "It works!"
module.exports =
  subscriptions: null

  activate: (state) ->
        # Events subscribed to in atom's system can be easily cleaned up with a CompositeDisposable
    @subscriptions = new CompositeDisposable

    # Register Hello, World!command that toggles this view
    @subscriptions.add atom.commands.add 'atom-workspace', 'racket-autocomplete:shouldIReplace': => @shouldIReplace()
    @subscriptions.add atom.workspace.observeTextEditors (editor) =>
      @handleEvents(editor)
  deactivate: ->
    @subscriptions.dispose()

  isParen: (text) ->
    return text == '('

  handleEvents: (e) ->
    editorTextInsertedSubscription = e.onDidInsertText (event) ->
      if @isParen(event.text)
        console.log "Ready to put some commands in sir!"

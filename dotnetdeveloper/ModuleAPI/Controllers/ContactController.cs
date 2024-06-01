using Microsoft.AspNetCore.Mvc;
using ModuleAPI.Context;
using ModuleAPI.Entities;

namespace ModuleAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class ContactController(AgendaContext context) : ControllerBase
{
    [HttpPost]
    public IActionResult Create(Contact contact)
    {
        context.Add(contact);
        context.SaveChanges();
        return CreatedAtAction(nameof(GetById), new { id = contact.Id }, contact);
    }

    [HttpGet("{id:int}")]
    public IActionResult GetById(int id)
    {
        var contact = context.Contacts.Find(id);

        if (contact == null) return NotFound();

        return Ok(context.Contacts.Find(id));
    }

    [HttpGet("name")]
    public IActionResult GetByName(string name)
    {
        var contacts = context.Contacts.Where(x => x.Name.Contains(name));

        return Ok(contacts);
    }

    [HttpPut("{id:int}")]
    public IActionResult Update(int id, Contact contact)
    {
        var existingContact = context.Contacts.Find(id);

        if (existingContact == null) return NotFound();

        existingContact.Name = contact.Name;
        existingContact.Phone = contact.Phone;
        existingContact.Active = contact.Active;

        context.Contacts.Update(existingContact);
        context.SaveChanges();

        return NoContent();
    }

    [HttpDelete("{id:int}")]
    public IActionResult Delete(int id)
    {
        var existingContact = context.Contacts.Find(id);

        if (existingContact == null) return NotFound();

        context.Contacts.Remove(existingContact);
        context.SaveChanges();

        return NoContent();
    }
}
